import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mongodb.*
import com.mongodb.util.JSON
import java.util.ArrayList

class DataBaseHandler {

    private val USER: String = "user"
    private val PASSWORD: String = "user"
    private val DB_URL = "mongodb://$USER:$PASSWORD@ds035438.mlab.com:35438/kotlin_mongo"

    private val DB_NAME = "kotlin_mongo"
    private val COLLECTION_NAME = "customers_data"
    private var collection: DBCollection? = null

    init {
        val mongoDB = MongoClient(MongoClientURI(DB_URL))
        val db: DB = mongoDB.getDB(DB_NAME)
        collection = db.getCollection(COLLECTION_NAME)
    }

    fun saveUser(user: User) {
        val userObject = JSON.parse(Gson().toJson(user)) as BasicDBObject
        collection!!.insert(userObject)
    }

    fun saveUsers(users: List<User>) {
        for(user in users) {
            val userObject = JSON.parse(Gson().toJson(user)) as BasicDBObject
            collection!!.insert(userObject)
        }
    }

    fun retrieveUser(email: String): User? {
        val queryObject = BasicDBObject()
        queryObject.put("email", email)
        val cursor = collection!!.find(queryObject)
        var user: User? = null
        if(cursor.hasNext())
                user = Gson().fromJson<User>(cursor.next().toString(), object: TypeToken<User>(){}.type)
        return user
    }

    fun retrieveUsers(): List<User>? {
        val cursor = collection!!.find()
        val avatars = ArrayList<User>()
        while (cursor.hasNext()) {
            val dbObject = cursor.next()
            val user = Gson().fromJson<User>(dbObject.toString(), User::class.java)
            avatars.add(user)
        }
        return avatars
    }

    fun deleteUser(user: User) {
        val queryObject = BasicDBObject()
        queryObject.put("name", user.name) //deleting by name
        val cursor = collection!!.find(queryObject)
        while(cursor.hasNext())
            collection!!.remove(cursor.next())

    }

    fun updateUser(user: User) {
        val queryObject = BasicDBObject()
        queryObject.put("name", user.name) //getting user by name
        val cursor = collection!!.find()
        while(cursor.hasNext()) {
            val currentUser = cursor.next()
            currentUser.put("name", user.name) //updating
            currentUser.put("email", user.email)
            currentUser.put("phone", user.phone)
            collection!!.save(currentUser)
        }
    }
}
