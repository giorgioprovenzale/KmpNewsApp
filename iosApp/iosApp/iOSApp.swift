import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
	var body: some Scene {
		WindowGroup {
            ContentView()
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        startKoin()

        return true
    }
}
