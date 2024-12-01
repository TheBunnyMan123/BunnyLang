package net.tkbunny.bunnylang.values

import net.tkbunny.bunnylang.BunnyLangValue

open class BunnyLangNil() : BunnyLangValue(null) {
  override val isFunction = false
  override val type = "nil"

  override fun toString(): String {
    return "nil"
  }
}

