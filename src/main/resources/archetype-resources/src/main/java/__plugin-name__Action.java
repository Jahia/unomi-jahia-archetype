package $package;

import org.apache.unomi.api.Event;
import org.apache.unomi.api.actions.Action;
import org.apache.unomi.api.actions.ActionExecutor;
import org.apache.unomi.api.services.EventService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A sample action :: Generated by Jahia - Unomi plugin archetype
 *
 */
public class ${plugin-name}Action implements ActionExecutor
{
private Logger logger = LoggerFactory.getLogger(${plugin-name}Action.class.getName());

    @Override
    public int execute(Action action, Event event) {
        //Write your action here
        logger.info("{} : Executed successfuly with params", "${plugin-name}Action");
        logger.info("Parameters : {}", action.getParameterValues());
        return EventService.PROFILE_UPDATED;
    }
}
