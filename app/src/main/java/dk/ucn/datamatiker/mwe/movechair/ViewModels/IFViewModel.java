package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import java.util.List;

public interface IFViewModel<T> {
    T getItem(int id);
    List<T> getAll();
}
