package day06

import java.util.regex.Pattern


@main
def main(): Unit = {

  val source = scala.io.Source.fromFile("/Users/janrobert/dev/aoc2022/aoc2022/src/main/scala/day06/input.txt")
  val lines = try source.mkString.split("\n").toList finally source.close()
  val buffer = lines.head.toList

  def result(windowSize: Int) = {
    val firstUnique = buffer.sliding(windowSize).flatMap(window => {
      if (window.toSet.size == windowSize) Some(window) else None
    })
      .toList
      .head
      .mkString
    buffer.mkString.indexOf(firstUnique) + windowSize
  }

  def part1() = result(4)

  def part2() = result(14)

  println(part1())
  println(part2())
}