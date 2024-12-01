package net.tkbunny.bunnylang.app

import net.tkbunny.bunnylang.BunnyLang

fun main(args: Array<String>) {
  print(BunnyLang().run(args.getOrElse(0, { "\"Please\" \"enter\" + \"BunnyLang\" + \"code\" + \"as\" + \"the\" + \"first\" + \"argument\" + println" })))
}
