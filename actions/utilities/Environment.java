package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:environmentConfig/${env}.properties" })
public interface Environment extends Config {
	@Key("App.Url")
	String appUserUrl();

	@Key("App.AdminUrl")
	String appAdminUrl();

	@Key("App.User")
	String appUsername();

	@Key("App.Pass")
	String appPassword();

	@Key("DB.Host")
	String dbHostname();

	@Key("DB.User")
	String dbUsername();

	@Key("DB.Pass")
	String dbPassword();

}
