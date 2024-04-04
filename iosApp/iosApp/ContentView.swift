import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    
    let domainComponent: DomainComponent
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(domainComponent: domainComponent)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    let domainComponent: DomainComponent
    
    var body: some View {
        ComposeView(domainComponent: domainComponent)
                .ignoresSafeArea(edges: .all)
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



