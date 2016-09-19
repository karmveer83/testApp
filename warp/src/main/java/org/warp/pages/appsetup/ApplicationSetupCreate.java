package org.warp.pages.appsetup;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.application.HostGroup;
import org.warp.services.dao.admin.HostGroupDAO;

@Import(stylesheet="appsetup.css")
public class ApplicationSetupCreate {
	@InjectPage
	private ApplicationSetup backTo;
	

	
	@Property
	private HostGroup hostGroup;
	
	@Property
	private String hostGroupPageName;
	
	@Inject
	private HostGroupDAO hostGroupCreateService;
	
    @InjectComponent("hostGroupCreateForm")
    private BeanEditForm hostGroupCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (hostGroupCreateForm.isValid()) {
        	hostGroup = new HostGroup();
        }
    }
    
    void onValidateFromHostGroupCreateForm() {



        if (hostGroupCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	hostGroupCreateService.add(hostGroup); 
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
