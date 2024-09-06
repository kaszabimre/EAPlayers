import ComposeApp
import SwiftUI

@main
struct iOSApp: App {
    // Lazy so it doesn't try to initialize before startKoin() is called
    lazy var logger = KoinApplication.getLogger(class: iOSApp.self)
    
    init() {
        KoinApplication.start()
        logger.v(throwable: nil, tag: "Logger",message: { "App Started" })
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
