import Shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        Platform_nativeKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
