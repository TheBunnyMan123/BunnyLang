package net.tkbunny.bunnylang.values

import net.tkbunny.bunnylang.BunnyLangValue
import net.tkbunny.bunnylang.values.BunnyLangNumber
import net.tkbunny.bunnylang.values.BunnyLangString

open class BunnyLangInteger(private var num: Long) : BunnyLangNumber(num) {
  override val isFunction = false
  override val type = "integer"
  
  fun add(operand: BunnyLangInteger): BunnyLangInteger {
    num += operand.getKotlinValue()
    return this
  }
  fun sub(operand: BunnyLangInteger): BunnyLangInteger {
    num -= operand.getKotlinValue()
    return this
  }
  fun mul(operand: BunnyLangInteger): BunnyLangInteger {
    num *= operand.getKotlinValue()
    return this
  }
  fun div(operand: BunnyLangInteger): BunnyLangInteger {
    num /= operand.getKotlinValue()
    return this
  }
  fun mod(operand: BunnyLangInteger): BunnyLangInteger {
    num %= operand.getKotlinValue()
    return this
  }


  open override fun add(operand: BunnyLangValue): BunnyLangValue {
    if (operand.type == "string") {
      return BunnyLangString(num.toString() + " " + operand.toString())
    }

    return add(BunnyLangInteger(operand.getKotlinValue().toString().toLong()))
  }
  open override fun sub(operand: BunnyLangValue): BunnyLangInteger {
    return sub(BunnyLangInteger(operand.getKotlinValue().toString().toLong()))
  }
  open override fun mul(operand: BunnyLangValue): BunnyLangInteger {
    return mul(BunnyLangInteger(operand.getKotlinValue().toString().toLong()))
  }
  open override fun div(operand: BunnyLangValue): BunnyLangInteger {
    return div(BunnyLangInteger(operand.getKotlinValue().toString().toLong()))
  }
  open override fun mod(operand: BunnyLangValue): BunnyLangInteger {
    return mod(BunnyLangInteger(operand.getKotlinValue().toString().toLong()))
  }
  override fun getKotlinValue(): Long {
    return num
  }
  override fun copy(): BunnyLangInteger {
    return BunnyLangInteger(num)
  }
}

