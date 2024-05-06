
import org.apache.flink.api.common.typeinfo.Types
import org.apache.flink.connector.datagen.source.DataGeneratorSource
import org.apache.flink.connector.datagen.source.GeneratorFunction


fun main(args: Array<String>) {
    val generatorFunction = GeneratorFunction<Long, String> { index -> "Number: $index" }
    val numberOfRecords= 1000L
    val source = DataGeneratorSource(generatorFunction, numberOfRecords, Types.STRING)
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}