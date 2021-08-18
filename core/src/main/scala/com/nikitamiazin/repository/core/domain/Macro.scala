package com.nikitamiazin.repository.core.domain

import scala.reflect.macros.blackbox

object Macro {

  def materializeExtractIdImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[ExtractId[T]] = {
    import c.universe._

    val idFieldName = "id"
    val idFieldTermName = TermName(idFieldName)
    val stringType = typeOf[String]

    def abort(msg: String): Nothing = c.abort(c.enclosingPosition, msg)

    def isCaseClass(t: Type): Boolean =
      t.typeSymbol.isClass && t.typeSymbol.asClass.isCaseClass && !t.typeSymbol.isModuleClass

    def isReturnsString(sym: MethodSymbol): Boolean =
      sym.returnType.dealias =:= stringType

    val mainType = weakTypeOf[T]

    if (!isCaseClass(mainType)) abort("Auto-derivation of ExtractId allowed only for case classes")

    val idField: MethodSymbol =
      mainType.members
        .collect { case m: MethodSymbol => m }
        .filter(m => m.isGetter && m.isParamAccessor)
        .find(_.name == idFieldTermName)
        .getOrElse(abort(s"Failed to derive ExtractId; no `$idFieldName` field found"))

    if (!isReturnsString(idField)) abort(s"Currently only `$stringType` id type is supported for auto-derivation")

    c.Expr[ExtractId[T]] {
      q"""
          import com.nikitamiazin.repository.core.domain.ExtractId
          new ExtractId[$mainType] {
            override type Id = $stringType
            override val idField: String = ${idFieldTermName.toString}
            override def id(t: $mainType): Id = t.$idFieldTermName
          }
      """
    }
  }

}
