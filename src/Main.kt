
/**
 * Created by animal505 on 11/06/17.
 */

fun main(args: Array<String>) {

    val handler = DataBaseHandler()

    //handler.saveUser(User("BUCETO", "BUCETSON"))
    /*val users = arrayListOf<User>()
    users.add(User("animal1", "email1"))
    users.add(User("animal2", "email2"))
    users.add(User("animal3", "email3"))
    users.add(User("animal4", "email4"))
    handler.saveUsers(users) */


    //val u = handler.retrieveUser("BUCETSON1")
    //println(u)


    //val users = handler.retrieveUsers()
    //for(user in users!!)
      //  println(user)

    //val user = User("BADU", "BADUZA1", "0800")
    //handler.deleteUser(User("animal2", "email2", "0800"))

    //val mock = User("Putao", "Puta1", "0800puto")
    //handler.saveUser(mock)
    val mockNew = User("Putao", "Putona", "6090putinho")
    handler.updateUser(mockNew)

}
