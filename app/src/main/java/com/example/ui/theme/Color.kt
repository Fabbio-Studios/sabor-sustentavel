package com.example.ui.theme

import androidx.compose.ui.graphics.Color

// Sustainable Earthy Neobrutalist Palette
// Requested colors: ["#386641","#6a994e","#a7c957","#f2e8cf","#bc4749"]
val EcoDeepGreen = Color(0xFF386641)  // Hunter / Forest green
val EcoLeafGreen = Color(0xFF6A994E)  // Foliage / Fresh leaf green
val EcoLime = Color(0xFFA7C957)       // Pistachio / Light sage green
val EcoParchment = Color(0xFFF2E8CF)  // Warm parchment canvas
val EcoRustRed = Color(0xFFBC4749)    // Terracotta / Berry rust red

// UI surface and typography tokens
val EcoSurface = Color(0xFFFFFFFF)    // Crisp white for card containers
val EcoInk = Color(0xFF1C2D20)        // Deep forest-black for maximum text legibility
val EcoBorder = Color(0xFF386641)     // Forest green border
val EcoMuted = Color(0xFFE5DCBF)      // Muted parchment for subtle borders / dividers

// Backward-compatible aliases mapped to the new palette
val NeoBackground = EcoParchment
val NeoWhite = EcoSurface
val NeoBlack = EcoInk
val NeoYellow = EcoLime
val NeoBorder = EcoBorder
val NeoRust = EcoRustRed
