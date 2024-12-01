package net.tkbunny.bunnylang.values

import net.tkbunny.bunnylang.BunnyLangValue

open class BunnyLangString(private var value: String) : BunnyLangValue(value) {
  override val isFunction = false
  override val type = "string"

  override fun add(operand: BunnyLangValue): BunnyLangString {
    value += " " + operand.toString()
    return this
  }

  override fun getKotlinValue(): String {
    return value
  }
}

