package day05

import java.util.regex.Pattern


@main
def main(): Unit = {

  val source = scala.io.Source.fromFile("/Users/janrobert/dev/aoc2022/aoc2022/src/main/scala/day05/input.txt")
  val lines = try source.mkString.split("\n\n").toList finally source.close()
  val Pattern = "move (\\d+) from (\\d+) to (\\d+)".r
  val moves = lines(1).mkString.split("\n")
    .toList
    .map(line => {
      line match
        case Pattern(amount, from, to) => (amount.toInt, from.toInt, to.toInt)
    })
  val boxLines = lines.head.mkString.split("\n").toList
  val lineLength = boxLines.head.length
  val indices = Range.inclusive(1, lineLength, 4)

  val boxes = boxLines
    .map(line => indices.map(i => line.charAt(i)))
    .filter(line => !line.startsWith("1"))
    .reverse
    .flatMap(_.zipWithIndex)
    .groupBy((_, i) => i + 1)
    .view
    .mapValues(list => list.map((c, _) => c.toString).filter(_ != " "))
    .toMap

  def performMoveOne(acc:  Map[Int, List[String]], move: (Int, Int, Int)) = {
      val (amount, from, to) = move
      val f = acc(from)
      val t = acc(to)
      val toMove = f.takeRight(amount)
      val newSource = f.dropRight(amount)
      val newTarget = t ++ toMove.reverse
      acc
        .updatedWith(from)(_.map(_ => newSource))
        .updatedWith(to)(_.map(_ => newTarget))

  }

  def performMoveTwo(acc: Map[Int, List[String]], move: (Int, Int, Int)) = {
    val (amount, from, to) = move
    val f = acc(from)
    val t = acc(to)
    val toMove = f.takeRight(amount)
    val newSource = f.dropRight(amount)
    val newTarget = t ++ toMove
    acc
      .updatedWith(from)(_.map(_ => newSource))
      .updatedWith(to)(_.map(_ => newTarget))

  }

  def part1() = {
    moves.foldLeft(boxes)((acc, move) => performMoveOne(acc, move))
      .toList
      .sortBy((k, _) => k)
      .map((_, v) => v.last)
      .mkString
  }

  def part2() = {
    moves.foldLeft(boxes)((acc, move) => performMoveTwo(acc, move))
      .toList
      .sortBy((k, _) => k)
      .map((_, v) => v.last)
      .mkString
  }
  println(part1())
  println(part2())
}