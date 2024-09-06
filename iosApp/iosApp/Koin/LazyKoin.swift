//
//  LazyKoin.swift
//  iosApp
//
//  Created by Imi Kaszab on 15/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import ComposeApp

@propertyWrapper
struct LazyKoin<T: AnyObject> {
    lazy var wrappedValue: T = { KoinApplication.shared.inject() }()

    init() { }
}
