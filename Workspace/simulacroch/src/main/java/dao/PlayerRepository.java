package dao;

import dao.model.Player;

import java.util.List;

public interface PlayerRepository {
    List<Player> getAll();

    int add(Player player);

    void update(Player playerDatabase);

    void delete(int idDelete, boolean answer);

    void restorePlayer(int id);

    void saveToXml(int playerId);
}
