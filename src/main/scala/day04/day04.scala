package day04

import java.util.regex.Pattern


@main
def main(): Unit = {

  val source = scala.io.Source.fromFile("/Users/janrobert/dev/aoc2022/aoc2022/src/main/scala/day04/input.txt")
  val lines = try source.mkString.split("\n").toList finally source.close()

  val Pattern = "(\\d+)-(\\d+),(\\d+)-(\\d+)".r

  def part1() = {
    lines
      .map(line => {
        line match
          case Pattern(a, b, c, d) =>
            val rangeA = Range.inclusive(a.toInt, b.toInt)
            val rangeB = Range.inclusive(c.toInt, d.toInt)
            val intersect = rangeA.intersect(rangeB)
            intersect.nonEmpty && (intersect.size == rangeA.size || intersect.size == rangeB.size)
      }).count(b => b)
  }

  def part2() = {
    lines
      .map(line => {
        line match
          case Pattern(a, b, c, d) =>
            val rangeA = Range.inclusive(a.toInt, b.toInt)
            val rangeB = Range.inclusive(c.toInt, d.toInt)
            val intersect = rangeA.intersect(rangeB)
            intersect.nonEmpty
      }).count(b => b)
  }
  println(part1())
  println(part2())
}