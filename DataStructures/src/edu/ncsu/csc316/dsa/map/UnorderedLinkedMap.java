package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** A PositionalList of entries */
    private PositionalList<Entry<K, V>> list;
    
    /** Constructs a PositionalList of entries */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * The position of the entry with the given key
     * 
     * @param key the key of the entry to search for
     * @return the position of the current entry
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
    	//null cases?
    	if (list.isEmpty()) {
    		return null;
    	}
        Position<Entry<K, V>> p = list.first();
        for (int i = 0; i < list.size(); i++) {
        	if (p.getElement().getKey().equals(key)) {
            	return p;
            } else { 
            	p = list.after(p);
            }
        }
        return null; // not sure what the null case is for look up yet
 
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null) {
        	return null;
        } else {
        	moveToFront(p);
        	return list.first().getElement().getValue();
        }
    }
    
    /**
     * Moves the given entry to the front of the list
     * @param position the position of the entry to be moved to the front
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	Entry<K, V> entry = list.remove(position);
    	list.addFirst(entry);
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p == null) {
        	list.addFirst(new MapEntry<K, V>(key, value));
        	return null;
        } else {
        	V oldVal = p.getElement().getValue();
        	((MapEntry<K, V>) p.getElement()).setValue(value);
        	moveToFront(p);
        	return oldVal;
        }
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) {
       	return null;
       } else {
       	V value = p.getElement().getValue();
       	list.remove(p);
       	return value;
       }
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}