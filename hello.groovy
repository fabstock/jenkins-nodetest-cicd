// Define a list of numbers
def numbers = [1, 2, 3, 4, 5]

// Use a closure to print each number
numbers.each { number ->
    println "Number: $number"
}

// Define a map
def person = [name: 'John', age: 30]

// Access map values
println "Name: ${person.name}, Age: ${person.age}"

// Define a class
class Greeter {
    String name

    void greet() {
        println "Hello, $name!"
    }
}

// Create an instance of the class and call the greet method
def greeter = new Greeter(name: 'Alice')
greeter.greet()

