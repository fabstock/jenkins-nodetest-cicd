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
    rb = RingBuffer(6)

    # Ajout d'éléments au buffer
    rb.enqueue(1)
    rb.enqueue(2)
    rb.enqueue(3)
    rb.enqueue(4)
    rb.enqueue(5)
    rb.enqueue(6)
    print(rb)  # Affiche le buffer rempli

    # Ajout d'un nouvel élément, ce qui écrase le plus ancien
    rb.enqueue(6)
    print(rb)  # Affiche le buffer avec le premier élément écrasé

    # Lecture et suppression des éléments du buffer
    while not rb.is_empty():
        value = rb.dequeue()
        print(f"Dequeued: {value}")
        print(rb)  # Affiche le buffer après chaque suppression

