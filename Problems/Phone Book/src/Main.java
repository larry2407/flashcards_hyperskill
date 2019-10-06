import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int idx = findKey(key);

            if (idx == -1) {
                this.rehash();
                return  put( key, value);
            }

            table[idx] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return (T) table[idx].getValue();
        }

        public void remove(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null || table[idx].isRemoved()) {
                return ;
            }
            table[idx].remove();
        }

        private int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            TableEntry[] oldTable = new TableEntry[size];
            for (int i = 0; i < size; i++) {
                oldTable[i] = this.table[i];
            }
            this.size *= 2;
            this.table = new TableEntry[size];
            for (TableEntry elt : oldTable) {
                put(elt.getKey(), (T) elt.getValue());
            }
        }
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        HashTable<String> hTable = new HashTable<>(5);
        for(int i=0; i<n; i++){
            treatment(sc.nextLine().trim(), hTable);
        }

    }

    private static void treatment(String line,  HashTable<String> t) {
        String[] query = line.split("\\s+");
        int key = Integer.parseInt(query[1]);
        if(query[0].equals("put")){
            t.put(key, query[2]);
            //System.out.println(t.get(Integer.parseInt(query[1])));
        }else if(query[0].equals("get")){
            String value = null == t.get(key) ? "-1" : t.get(key);
            System.out.println(value);
        }else if(query[0].equals("remove")){
            t.remove(key);
        }
    }
}