import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
    var rootHolder: RootHolder {
        appDelegate.getRootHolder()
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView(domainComponent: rootHolder.domainComponent)
		}
	}
}

class RootHolder: ObservableObject {

    let domainComponent: DomainComponent
    
    init() {
        domainComponent = koin.domainComponent
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    var rootHolder: RootHolder? = nil
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        startKoin()
        rootHolder = RootHolder()
        
        return true
    }
    
    func getRootHolder() -> RootHolder {
        if(rootHolder == nil) {
            rootHolder = RootHolder()
        }
        return rootHolder!
    }
}
