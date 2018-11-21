package dk.ucn.datamatiker.mwe.movechair.Data;

public interface DAOIF<T> {

        void getConnection(DAOIF<T> db);

        T getItem(int id);

}
