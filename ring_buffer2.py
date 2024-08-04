class RingBuffer:
    def __init__(self, size):
        self.size = size
        self.buffer = [None] * size
        self.head = 0
        self.tail = 0
        self.count = 0

    def is_full(self):
        return self.count == self.size

    def is_empty(self):
        return self.count == 0

    def enqueue(self, value):
        if self.is_full():
            print("Buffer is full. Overwriting oldest data.")
            self.tail = (self.tail + 1) % self.size
        else:
            self.count += 1
        self.buffer[self.head] = value
        self.head = (self.head + 1) % self.size

    def dequeue(self):
        if self.is_empty():
            raise IndexError("Buffer is empty")
        value = self.buffer[self.tail]
        self.buffer[self.tail] = None
        self.tail = (self.tail + 1) % self.size
        self.count -= 1
        return value

    def __repr__(self):
        return f"RingBuffer({self.buffer})"

# Exemple d'utilisation du RingBuffer
if __name__ == "__main__":
    rb = RingBuffer(5)

    while True:
        action = input("Choisissez une action (enqueue, dequeue, quit) : ").strip().lower()
        
        if action == "enqueue":
            value = int(input("Entrez une valeur à ajouter : "))
            rb.enqueue(value)
            print(rb)
        elif action == "dequeue":
            try:
                value = rb.dequeue()
                print(f"Dequeued: {value}")
                print(rb)
            except IndexError as e:
                print(e)
        elif action == "quit":
            break
        else:
            print("Action non valide. Veuillez choisir 'enqueue', 'dequeue' ou 'quit'.")

