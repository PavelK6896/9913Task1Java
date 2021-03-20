package app.web.pavelk.task1;

import app.web.pavelk.task1.model.Project;
import app.web.pavelk.task1.model.init.DataBase;
import app.web.pavelk.task1.model.init.H2;
import app.web.pavelk.task1.model.init.UploadFile;
import app.web.pavelk.task1.model.repository.ProjectRep;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DataBase dataBase = new H2();
        dataBase.create();
        dataBase.loadInit(dataBase, UploadFile.getFile());
        ProjectRep projectRep = new ProjectRep();
        System.out.println(projectRep.get(dataBase, Project.class));

    }
}
