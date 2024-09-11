package de.affenherzog.knockbackffa.config;

public interface Container<T> {

  T getRandom(T currentlySelected);

}
