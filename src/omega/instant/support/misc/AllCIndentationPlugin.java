package omega.instant.support.misc;
import omega.instant.support.IndentationFrameworks;

import java.net.URL;

import omega.plugin.Plugin;
import omega.plugin.PluginCategory;
public class AllCIndentationPlugin implements Plugin{

	private AllInOneCIndentationFramework framework;

	@Override
	public boolean init(){
		framework = new AllInOneCIndentationFramework();
		return true;
	}

	@Override
	public boolean enable(){
		IndentationFrameworks.add(framework);
		return true;
	}

	@Override
	public boolean disable(){
		IndentationFrameworks.indentationFrameworks.remove(framework);
		return true;
	}
	
	@Override
	public String getName() {
		return "All-In-One C Auto Indent";
	}
	
	@Override
	public String getVersion() {
		return "v2.2";
	}
	
	@Override
	public String getPluginCategory() {
		return PluginCategory.EDITING;
	}
	
	@Override
	public String getDescription() {
		return "Can Auto-Indent *.c, *.cpp & *.cs files.";
	}
	
	@Override
	public String getAuthor() {
		return "Omega UI";
	}
	
	@Override
	public String getLicense() {
		return "GNU GPL v3";
	}
	
	@Override
	public String getSizeInMegaBytes() {
		return "0.01 MB";
	}
	
	@Override
	public URL getImage() {
		try{
			return new URL("https://raw.githubusercontent.com/omegaui/omegaide/main/res/fluent-icons/icons8-python-48.png");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean needsRestart() {
		return false;
	}
}
