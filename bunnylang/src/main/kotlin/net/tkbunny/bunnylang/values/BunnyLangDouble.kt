package net.tkbunny.bunnylang.values

import net.tkbunny.bunnylang.BunnyLangValue
import net.tkbunny.bunnylang.values.BunnyLangNumber

open class BunnyLangDouble(var num: Double) : BunnyLangNumber(num) {
  override val isFunction = false
  override val type = "double"
  
  fun add(operand: BunnyLangDouble): BunnyLangDouble {
    num += operand.getKotlinValue()
    return this
  }
  fun sub(operand: BunnyLangDouble): BunnyLangDouble {
    num -= operand.getKotlinValue()
    return this
  }
  fun mul(operand: BunnyLangDouble): BunnyLangDouble {
    num *= operand.getKotlinValue()
    return this
  }
  fun div(operand: BunnyLangDouble): BunnyLangDouble {
    num /= operand.getKotlinValue()
    return this
  }
  fun mod(operand: BunnyLangDouble): BunnyLangDouble {
    num %= operand.getKotlinValue()
    return this
  }

  open override fun add(operand: BunnyLangValue): BunnyLangDouble {
    return add(BunnyLangDouble(operand.getKotlinValue().toString().toDouble()))
  }
  open override fun sub(operand: BunnyLangValue): BunnyLangDouble {
    return sub(BunnyLangDouble(operand.getKotlinValue().toString().toDouble()))
  }
  open override fun mul(operand: BunnyLangValue): BunnyLangDouble {
    return mul(BunnyLangDouble(operand.getKotlinValue().toString().toDouble()))
  }
  open override fun div(operand: BunnyLangValue): BunnyLangDouble {
    return div(BunnyLangDouble(operand.getKotlinValue().toString().toDouble()))
  }
  open override fun mod(operand: BunnyLangValue): BunnyLangDouble {
    return mod(BunnyLangDouble(operand.getKotlinValue().toString().toDouble()))
  }

  override fun getKotlinValue(): Double {
    return num
  }
  override fun copy(): BunnyLangDouble {
    return BunnyLangDouble(num)
  }
}

