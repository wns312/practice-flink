import org.apache.flink.api.common.eventtime.WatermarkStrategy
import org.apache.flink.api.common.typeinfo.Types
import org.apache.flink.connector.datagen.source.DataGeneratorSource
import org.apache.flink.streaming.api.datastream.DataStreamSource
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment


fun main(args: Array<String>) {
    val env = StreamExecutionEnvironment.createLocalEnvironment()
    val numberOfRecords = 1000L
    val source = DataGeneratorSource({ index: Long -> "Number: $index" }, numberOfRecords, Types.STRING)

    val stream: DataStreamSource<String> = env.fromSource(
        source,
        WatermarkStrategy.noWatermarks(),
        "Generator Source"
    )

    stream.print()
    env.execute()
}