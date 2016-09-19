package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.Host;
import org.warp.services.dao.admin.HostDAO;

@Import(stylesheet="appsetup.css")
public class HostSetupCreate {
	@InjectPage
	private HostSetup backTo;
	

	
	@Property
	private Host host;
	
	@Property
	private String hostPageName;
	
	@Inject
	private HostDAO hostCreateService;
	
    @InjectComponent("hostCreateForm")
    private BeanEditForm hostCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (hostCreateForm.isValid()) {
        	host = new Host();
        }
    }
    
    void onValidateFromHostCreateForm() {



        if (hostCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	hostCreateService.add(host); 
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
