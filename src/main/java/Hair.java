import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Hair {

    private String description;

    private boolean completed;

    private LocalDateTime createdAt;

    private int id;

    private int categoryId;

    public Hair(String description, int categoryId) {

        this.description = description;

        completed = false;

        createdAt = LocalDateTime.now();

        this.categoryId = categoryId;

    }

    public String getDescription() {

        return description;

    }

    public boolean isCompleted() {

        return completed;

    }

    public LocalDateTime getCreatedAt() {

        return createdAt;

    }

    public int getCategoryId() {

        return categoryId;

    }

    public int getId() {

        return id;

    }

    public static List<Hair> all() {

        String sql = "SELECT id, description, categoryId FROM hair_saloon";

        try (Connection con = DB.sql2o.open()) {

            return con.createQuery(sql).executeAndFetch(Hair.class);

        }

    }

    @Override

    public boolean equals(Object otherHair) {

        if (!(otherHair instanceof Hair)) {

            return false;

        } else {

            Hair newHair = (Hair) otherHair;

            return this.getDescription().equals(newHair.getDescription()) &&

                    this.getId() == newHair.getId() &&

                    this.getCategoryId() == newHair.getCategoryId();

        }

    }

    public void save() {

        try (Connection con = DB.sql2o.open()) {

            String sql = "INSERT INTO hair_saloon(description, categoryId) VALUES (:description, :categoryId)";

            this.id = (int) con.createQuery(sql, true)

                    .addParameter("description", this.description)

                    .addParameter("categoryId", this.categoryId)

                    .executeUpdate()

                    .getKey();

        }

    }

    public static Hair find(int id) {

        try (Connection con = DB.sql2o.open()) {

            String sql = "SELECT * FROM hair_saloon where id=:id";

            Hair hair = con.createQuery(sql)

                    .addParameter("id", id)

                    .executeAndFetchFirst(Hair.class);

            return hair;

        }

    }

    public void update(String description) {

        try (Connection con = DB.sql2o.open()) {

            String sql = "UPDATE hair_saloon SET description = :description WHERE id = :id";

            con.createQuery(sql)

                    .addParameter("description", description)

                    .addParameter("id", id)

                    .executeUpdate();

        }

    }

    public void delete() {

        try (Connection con = DB.sql2o.open()) {

            String sql = "DELETE FROM hair WHERE id = :id;";

            con.createQuery(sql)

                    .addParameter("id", id)

                    .executeUpdate();

        }

    }

}