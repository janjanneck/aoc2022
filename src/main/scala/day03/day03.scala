
@main
def main(): Unit = {

  val source = scala.io.Source.fromFile("/Users/janrobert/dev/aoc2022/aoc2022/src/main/scala/day03/input.txt")
  val lines = try source.mkString.split("\n").toList finally source.close()

  def charToInt(char: Char) = if (char.isUpper) {
    char.toInt - 38
  } else {
    char.toInt - 96
  }

  def part1() = {
    lines
      .map(line => line.splitAt(line.length / 2))
      .map((head, tail) => (head.toCharArray.toSet, tail.toCharArray.toSet))
      .map((head, tail) => head.intersect(tail))
      .map(_.head)
      .map(charToInt)
      .sum
  }

  def splitList(input: List[String], acc: List[List[String]]): List[List[String]]= {
    (input: @unchecked) match
      case x :: y :: z :: tail => splitList(tail, List(x, y, z) :: acc)
      case Nil => acc
  }

  def part2() = {
      splitList(lines, List.empty)
        .map(_.map(_.toCharArray.toSet.toList) match
            case List(x, y, z) => x.intersect(y).intersect(z).head
        )
        .map(charToInt)
        .sum
  }
  println(part1())
  println(part2())
}