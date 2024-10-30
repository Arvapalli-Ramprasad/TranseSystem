
package di
import dagger.Module
import dagger.Provides
import org.example.constants.Constants
import org.w3c.dom.Document
import javax.inject.Named
import javax.xml.parsers.DocumentBuilderFactory

@Module
class ConfigModule(private val xmlFilePath: String) {

    private val config: Config by lazy { loadConfig() }

    private fun loadConfig(): Config {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document: Document = builder.parse(xmlFilePath)
        document.documentElement.normalize()

        // Extract MongoDB configuration
        val mongoNode = document.getElementsByTagName("mongo").item(0)
        val dbHost = mongoNode.childNodes.item(1).textContent
        val dbPort = mongoNode.childNodes.item(3).textContent.toInt()
        val dbName = mongoNode.childNodes.item(5).textContent

        // Extract Server configuration
        val serverNode = document.getElementsByTagName("server").item(0)
        val serverHost = serverNode.childNodes.item(1).textContent
        val serverPort = serverNode.childNodes.item(3).textContent.toInt()

        // Extract Kafka configuration
        val kafkaNode = document.getElementsByTagName("entry")
        val kafkaConfig = mutableMapOf<String, String>()

        for (i in 0 until kafkaNode.length) {
            val entry = kafkaNode.item(i)
            val key = entry.attributes.getNamedItem("key").textContent
            val value = entry.textContent
            kafkaConfig[key] = value
        }

        return Config(
            dbHost, dbPort, dbName,
            serverHost, serverPort,
            kafkaConfig[Constants.KAFKA_BOOTSTRAP_SERVERS] ?: "",
            kafkaConfig[Constants.KAFKA_USERNAME] ?: "",
            kafkaConfig[Constants.KAFKA_PASSWORD] ?: "",
            kafkaConfig[Constants.KAFKA_TOPIC] ?: ""
        )
    }

    @Provides
    @Named(Constants.SERVER_HOST)
    fun provideServerHost(): String = config.serverHost

    @Provides
    @Named(Constants.SERVER_PORT)
    fun provideServerPort(): Int = config.serverPort

    @Provides
    @Named(Constants.MONGO_DB_HOST)
    fun provideDbHost(): String = config.dbHost

    @Provides
    @Named(Constants.MONGO_DB_PORT)
    fun provideDbPort(): Int = config.dbPort

    @Provides
    @Named(Constants.MONGO_DB_NAME)
    fun provideDbName(): String = config.dbName

    // Kafka providers
    @Provides
    @Named(Constants.KAFKA_BOOTSTRAP_SERVERS)
    fun provideKafkaBootstrapServers(): String = config.kafkaBootstrapServers

    @Provides
    @Named(Constants.KAFKA_USERNAME)
    fun provideKafkaUsername(): String = config.kafkaUsername

    @Provides
    @Named(Constants.KAFKA_PASSWORD)
    fun provideKafkaPassword(): String = config.kafkaPassword

    @Provides
    @Named(Constants.KAFKA_TOPIC)
    fun provideKafkaTopic(): String = config.kafkaTopic

    private data class Config(
        val dbHost: String,
        val dbPort: Int,
        val dbName: String,
        val serverHost: String,
        val serverPort: Int,
        val kafkaBootstrapServers: String,
        val kafkaUsername: String,
        val kafkaPassword: String,
        val kafkaTopic: String
    )
}

