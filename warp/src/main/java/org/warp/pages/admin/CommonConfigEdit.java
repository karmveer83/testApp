package org.warp.pages.admin;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.warp.entities.siteadmin.CommonConfig;
import org.warp.pages.admin.CommonConfigSetup;
import org.warp.services.dao.admin.CommonConfigDAO;

@Import(stylesheet="appsetup.css")
public class CommonConfigEdit {
	@Property
	CommonConfig commonConfig;
	
	@Inject
	CommonConfigDAO commonConfigFinder;
	
	private Long commonConfigId;
	
    @InjectComponent("commonConfigForm")
    private BeanEditForm commonConfigForm;

	@InjectPage
	private CommonConfigSetup backTo;
	 
	 void onActivate(Long commonConfigId) {
	        this.commonConfigId = commonConfigId;
	    }
	    
	    Long onPassivate() {
	        return commonConfigId;
	    }
	    
	    void onPrepareForRender() throws Exception {

	        // If fresh start, make sure there's a Person object available.

	        if (commonConfigForm.isValid()) {
	            commonConfig = commonConfigFinder.findCommonConfig(commonConfigId);

	            // We'd like to handle null person in the template, but can't because we're in BeanEditpageEditForm 
	            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

	            if (commonConfig == null) {
	                throw new Exception("CommonConfig " + commonConfigId + " does not exist.");
	            }
	        }

	    }

	    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

	    void onPrepareForSubmit() {

	        // Get Person object for the form fields to overlay.
	        commonConfig = commonConfigFinder.findCommonConfig(commonConfigId);

	        if (commonConfig == null) {
	        	commonConfigForm.recordError("Person has been deleted by another process.");
	            // Instantiate an empty person to avoid NPE in the BeanEditpageEditForm
	            commonConfig = new CommonConfig();
	        }
	    }

	    void onValidateFromCommonConfigForm() {


	        if (commonConfigForm.getHasErrors()) {
	            // We get here only if a server-side validator detected an error.
	            return;
	        }

	        try {
	        	commonConfigFinder.update(commonConfig); 
	        }
	        catch (Exception e) {
	            // Display the cause. In a real system we would try harder to get a commonConfig-friendly message.
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
