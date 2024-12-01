package net.tkbunny.bunnylang.values

import net.tkbunny.bunnylang.BunnyLangValue

open class BunnyLangNumber(private var valu: Number) : BunnyLangValue(valu) {
  override val isFunction = false
  override val type = "number"

  open fun add(operand: BunnyLangNumber): BunnyLangNumber {
    return this
  }
  open fun sub(operand: BunnyLangNumber): BunnyLangNumber {
    return this
  }
  open fun mul(operand: BunnyLangNumber): BunnyLangNumber {
    return this
  }
  open fun div(operand: BunnyLangNumber): BunnyLangNumber {
    return this
  }
  open fun mod(operand: BunnyLangNumber): BunnyLangNumber {
    return this
  }

  open override fun add(operand: BunnyLangValue): BunnyLangValue {
    return add(BunnyLangNumber(operand.getKotlinValue().toString().toLong()))
  }
  open override fun sub(operand: BunnyLangValue): BunnyLangNumber {
    return sub(BunnyLangNumber(operand.getKotlinValue().toString().toLong()))
  }
  open override fun mul(operand: BunnyLangValue): BunnyLangNumber {
    return mul(BunnyLangNumber(operand.getKotlinValue().toString().toLong()))
  }
  open override fun div(operand: BunnyLangValue): BunnyLangNumber {
    return div(BunnyLangNumber(operand.getKotlinValue().toString().toLong()))
  }
  open override fun mod(operand: BunnyLangValue): BunnyLangNumber {
    return mod(BunnyLangNumber(operand.getKotlinValue().toString().toLong()))
  }

  override fun getKotlinValue(): Number {
    return valu
  }
}

