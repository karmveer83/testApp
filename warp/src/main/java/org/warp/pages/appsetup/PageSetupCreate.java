package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.admin.ActionURL;
import org.warp.services.dao.admin.ActionURLDAO;

@Import(stylesheet="appsetup.css")
public class PageSetupCreate {
	@InjectPage
	private PageSetup backTo;
	

	
	@Property
	private ActionURL actionURL;
	
	@Property
	private String actionURLPageName;
	
	@Inject
	private ActionURLDAO pageCreateService;
	
    @InjectComponent("pageCreateForm")
    private BeanEditForm pageCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (pageCreateForm.isValid()) {
        	actionURL = new ActionURL();
        }
    }
    
    void onValidateFromPageCreateForm() {



        if (pageCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	pageCreateService.add(actionURL); 
        }
        catch (Exception e) {
            
        	e.printStackTrace();
        }
    }
	
    Object onSuccess() {
        
        return backTo;
    }

    void onRefresh() {
        // By doing nothing the page will be displayed afresh.
    }
	 


}
