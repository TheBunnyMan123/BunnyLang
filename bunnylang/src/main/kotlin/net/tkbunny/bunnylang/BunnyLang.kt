package net.tkbunny.bunnylang

import net.tkbunny.bunnylang.BunnyLangValue
import net.tkbunny.bunnylang.values.BunnyLangNil
import net.tkbunny.bunnylang.values.BunnyLangDouble
import net.tkbunny.bunnylang.values.BunnyLangInteger
import net.tkbunny.bunnylang.values.BunnyLangString
import java.util.Stack

class BunnyLang(val INSTRUCTION_LIMIT: Long = Long.MAX_VALUE) {
  val stack: Stack<BunnyLangValue> = Stack()
  var instructions = 0

  private val vars: HashMap<String, BunnyLangValue> = HashMap()

  private val numberRegex: Regex = Regex("^[0-9.]$")
  private val intRegex: Regex = Regex("^[0-9]$")
  private val stringRegex: Regex = Regex("^\"(.*)\"$")
  private val setRegex: Regex = Regex("^set .*$")
  private val whitespaceRegex: Regex = Regex("^\\s")

  fun isNumber(arg: Any): Boolean {
    if (arg is Int || arg is Double || arg is Long || arg is Float || numberRegex.matches(arg.toString())) {
      return true
    } else {
      return false
    }
  }

  fun getValueOf(arg: Any?): BunnyLangValue {
    val argStr = arg.toString()

    if (arg == null) {
      return BunnyLangNil()
    } else if (isNumber(arg)) {
      if (arg is Int || arg is Long) {
        return BunnyLangInteger(argStr.toLong())
      } else if (arg is Double || arg is Float) {
        return BunnyLangDouble(argStr.toDouble())
      } else if (intRegex.matches(argStr)) {
        return BunnyLangInteger(argStr.toLong())
      } else if (numberRegex.matches(argStr)) {
        return BunnyLangDouble(argStr.toDouble())
      }

      return BunnyLangInteger(0)
    } else if (stringRegex.matches(argStr)) {
      val match = stringRegex.find(argStr)
      if (match == null) {
        return BunnyLangNil()
      }
      return BunnyLangString(match.groupValues.getOrElse(1, { match.value }))
    } else {
      return vars.getOrDefault(argStr, BunnyLangNil())
    }
  }

  fun evalExpression(expr: String): String {
    val tokens: List<String> = expr.split(" ")
    var out = ""

    for (token in tokens) {
      instructions++
      when (token) {
        "+" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          stack.push(lhs.add(rhs))
        }
        "-" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          stack.push(lhs.sub(rhs))
        }
        "*" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          stack.push(lhs.mul(rhs))
        }
        "/" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          stack.push(lhs.div(rhs))
        }
        "%" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          stack.push(lhs.mod(rhs))
        }
        ">" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          if (lhs.getKotlinValue().toString().toLong() > rhs.getKotlinValue().toString().toLong()) {
            stack.push(BunnyLangInteger(1))
          } else {
            stack.push(BunnyLangInteger(0))
          }
        }
        "=" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          if (lhs.getKotlinValue().toString().toLong() == rhs.getKotlinValue().toString().toLong()) {
            stack.push(BunnyLangInteger(1))
          } else {
            stack.push(BunnyLangInteger(0))
          }
        }
        "<" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          if (lhs.getKotlinValue().toString().toLong() < rhs.getKotlinValue().toString().toLong()) {
            stack.push(BunnyLangInteger(1))
          } else {
            stack.push(BunnyLangInteger(0))
          }
        }
        ">=" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          if (lhs.getKotlinValue().toString().toLong() >= rhs.getKotlinValue().toString().toLong()) {
            stack.push(BunnyLangInteger(1))
          } else {
            stack.push(BunnyLangInteger(0))
          }
        }
        "<=" -> {
          val rhs = stack.pop()
          val lhs = stack.pop()

          if (lhs.getKotlinValue().toString().toLong() <= rhs.getKotlinValue().toString().toLong()) {
            stack.push(BunnyLangInteger(1))
          } else {
            stack.push(BunnyLangInteger(0))
          }
        }
        else -> stack.push(getValueOf(token))
      }
    }

    return out
  }

  fun run(code: String): String {
    var out = ""

    val loops: Stack<Int> = Stack()
    val lines: List<String> = code.split("\n")

    var lineNum= 0

    while (lineNum < lines.size) {
      instructions++
      val line = whitespaceRegex.replace(lines.get(lineNum), "")

      if (setRegex.matches(line)) {
        val variable: String = line.split(" ").get(1)
        vars.set(variable, stack.pop())
      } else if (line == "switch") {
        val top = stack.pop()
        val bottom = stack.pop()

        stack.push(top)
        stack.push(bottom)
      } else if (line == "print") {
        out += stack.pop().toString()
      } else if (line == "println") {
        out += stack.pop().toString() + "\n"
      } else if (line == "duplicate") {
        val toDupe = stack.last()
        
        stack.push(toDupe.copy())
      } else if (line == "loop") {
        val check = stack.last()

        if (check.getKotlinValue().toString().toLong() > 0) {
          loops.push(lineNum)
          lineNum++
        } else {
          var loopCount = 1

          lineNum++
          while (loopCount >= 1) {
            var currLine = lines.get(lineNum)
            when (currLine) {
              "loop" -> loopCount++
              "end" -> loopCount--
            }

            lineNum++
          }
        }

        continue
      } else if (line == "end") {
        val loopNum = loops.pop()
        val check = stack.last()

        if (check.getKotlinValue().toString().toLong() <= 0) {
          lineNum++
        } else {
          lineNum = loopNum
        }

        continue
      } else {
        evalExpression(line)
      }

      lineNum++
    }

    return out
  }
}
