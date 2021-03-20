package app.web.pavelk.task1.model.init;

import app.web.pavelk.task1.model.Project;
import app.web.pavelk.task1.model.Task;
import app.web.pavelk.task1.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoadJson {
    List<User> users;
    List<Project> projects;
    List<Task> tasks;
}
