
public class Main {
    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        Node<Integer> node = head.add(2);
        node = node.add(3);
        node = node.add(4);
        node = node.add(5);
//        Node<Integer> cycle = node;
        node = node.add(6);
        node = node.add(7);
        node = node.add(8);
        node = node.add(9);
        node = node.add(10);
        node = node.add(11);
//        node.next = cycle;

        node = isCycle(head);
        if(node != null) {
            System.out.println("Цикл есть, встретились на значении "+node.value);
            Node<Integer> cycleBegin = getCycleBegin(head,node);
            System.out.println("Начало цикла на значении "+cycleBegin.value);
            System.out.println("Длина цикла "+getCycleLength(cycleBegin)+" элементов.");
        } else {
            System.out.println("Циклов нет.");

            System.out.println();
            System.out.println("Исходный массив:");
            for(node = head; node != null; node = node.next) {
                System.out.print(node.value+" ");
            }

            System.out.println();
            int k = 3;
            node = findElement(head,k);
            if(node != null) {
                System.out.println("Значение "+k+"-го с конца элемента равно "+node.value);
            } else {
                System.out.println("Нет такого элемента.");
            }

            System.out.println();
            node = head;
            head = rotate(head.next,head);
            node.next = null;
            System.out.println("Перевернутый массив:");
            for (node = head; node != null; node = node.next) {
                System.out.print(node.value+" ");
            }
        }
    }

    public static <T> Node<T> findElement(Node<T> head, int numFromEnd) {
        Node<T> first = head;
        Node<T> second = head;
        int i = 0;
        while (i < numFromEnd) {
            if(first == null) {
                return null;
            } else {
                first = first.next;
                i++;
            }
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    public static <T> Node<T> rotate(Node<T> current, Node<T> previous) {
        if(current == null) {
            return previous;
        } else {
            Node<T> tmp = current.next;
            current.next = previous;
            return rotate(tmp,current);
        }
    }

    public static <T> Node<T> getCycleBegin(Node<T> head, Node<T> meetPoint) {
        while (head != meetPoint) {
            head = head.next;
            meetPoint = meetPoint.next;
        }
        return meetPoint;
    }

    public static <T> int getCycleLength(Node<T> cycleBeg) {
        Node<T> node = cycleBeg.next;
        int length = 1;
        while (node != cycleBeg) {
            node = node.next;
            length++;
        }
        return length;
    }

    public static <T> Node isCycle(Node<T> head) {
        Node<T> fast = head;
        Node<T> slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                return slow;
            }
        }
        return null;
    }

    private static class Node<T> {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
            next = null;
        }

        public Node add(T value) {
            this.next = new Node(value);
            return this.next;
        }

    }
}