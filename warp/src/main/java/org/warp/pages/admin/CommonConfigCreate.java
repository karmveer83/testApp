package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.siteadmin.CommonConfig;
import org.warp.services.dao.admin.CommonConfigDAO;

@Import(stylesheet="appsetup.css")
public class CommonConfigCreate {
	@InjectPage
	private CommonConfigSetup backTo;
	

	
	@Property
	private CommonConfig commonConfig;
	
	@Property
	private String commonConfigPageName;
	
	@Inject
	private CommonConfigDAO commonConfigCreateService;
	
    @InjectComponent("commonConfigCreateForm")
    private BeanEditForm commonConfigCreateForm;
    
    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (commonConfigCreateForm.isValid()) {
        	commonConfig = new CommonConfig();
        }
    }
    
    void onValidateFromCommonConfigCreateForm() {



        if (commonConfigCreateForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
        	commonConfigCreateService.add(commonConfig); 
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
