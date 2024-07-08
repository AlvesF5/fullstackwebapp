import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.google.cloud.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class TimestampDeserializer : JsonDeserializer<Timestamp>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Timestamp {
        val dateString = p.text
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date = dateFormat.parse(dateString)
        return Timestamp.of(date)
    }
}