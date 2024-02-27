package utils;

import java.util.List;

public interface MyIList<T> {

    void add(T e);
    List<T> getContent();

    void clear();

}
