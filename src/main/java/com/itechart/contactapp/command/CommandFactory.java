package com.itechart.contactapp.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.sql.DataSource;

public class CommandFactory {

    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    public static Command getCommand(ServletRequest request) {
        DataSource dataSource = (DataSource) request.getServletContext().getAttribute("dataSource");
        String theCommand = request.getParameter("command");
        log.info("Command = {}", theCommand);
        if (theCommand == null) {
            theCommand = "list";
        }
        switch (theCommand) {
            case "list":
                return new ShowContactsCommand(dataSource);
            case "editContact":
                return new EditContactCommand(dataSource);
            case "deleteContact":
                return new DeleteContactCommand(dataSource);
            case "createContact":
                return new CreateContactCommand();
            case "saveContact":
                return new SaveContactCommand(dataSource);
            case "removeAttachment":
                return new RemoveAttachmentCommand(dataSource);
            case "addAttachment":
                return new AddAttachmentCommand(dataSource);
            default:
                return null;
        }
    }
}