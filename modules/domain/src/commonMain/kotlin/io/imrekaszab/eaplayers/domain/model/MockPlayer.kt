package io.imrekaszab.eaplayers.domain.model

object MockPlayer {
    fun createMockPlayer() = Player(
        id = 231747,
        rank = 1,
        overallRating = 91,
        firstName = "Kylian",
        lastName = "Mbappé",
        commonName = null,
        birthdate = "12/20/1998 12:00:00 AM",
        height = 182,
        skillMoves = 5,
        weakFootAbility = 4,
        attackingWorkRate = 0, // Example value, adjust as needed
        defensiveWorkRate = 0, // Example value, adjust as needed
        avatarUrl = "https://ratings-images-prod.pulse.ea.com/FC25/full/" +
                "player-portraits/p231747.png?padding=0.7",
        shieldUrl = "https://ratings-images-prod.pulse.ea.com/FC25/full/" +
                "player-shields/en/231747.png?width=265",
        nationality = Nationality(
            id = 18,
            label = "France",
            imageUrl = "https://images.ctfassets.net/rs6bgs1g8dbr/" +
                    "5Il39kdF0vuuJ1Gc18R7Ww/b982b6f565d2d59fe0dc61e5ca620092/f_18.png"
        ),
        team = Team(
            id = 243,
            label = "Real Madrid",
            imageUrl = "https://images.ctfassets.net/rs6bgs1g8dbr/3X9LsZRgy03pchwhQacpwY/f6a0a73d564f4e5653e88427c0b4a02c/Real_Madrid.png",
            isPopular = true
        ),
        position = Position(
            id = "25",
            shortLabel = "ST",
            label = "Striker"
        ),
        playerAbilities = listOf(
            PlayerAbility(
                id = "trait1_1",
                label = "Finesse Shot",
                description = "Faster finesse shots with more curve and accuracy",
                imageUrl = "https://images.ctfassets.net/rs6bgs1g8dbr/" +
                        "468u1R1p2k1fA6WBHi0OEn/fb9d49ed2a8fb500cd5a184937b96b34/Finesse_Shot.png",
                type = AbilityType(id = "playStyle", label = "Play Style")
            )
        ),
        teamMates = listOf(createMockTeammate())
    )

    private fun createMockTeammate() = Player(
        id = 252371,
        rank = 5,
        overallRating = 90,
        firstName = "Jude",
        lastName = "Bellingham",
        commonName = null,
        birthdate = "6/29/2003 12:00:00 AM",
        height = 186,
        skillMoves = 4,
        weakFootAbility = 4,
        attackingWorkRate = 0, // Example value, adjust as needed
        defensiveWorkRate = 0, // Example value, adjust as needed
        avatarUrl = "https://ratings-images-prod.pulse.ea.com/FC25/full/" +
                "player-portraits/p252371.png?padding=0.7",
        shieldUrl = "https://ratings-images-prod.pulse.ea.com/FC25/full/" +
                "player-shields/en/252371.png?width=265",
        nationality = Nationality(
            id = 14,
            label = "England",
            imageUrl = "https://images.ctfassets.net/rs6bgs1g8dbr/" +
                    "4BVs0BAE16wJ4ihZS72xv8/5f2bab28b731170d875d4b0dd66b3664/f_14.png"
        ),
        team = Team(
            id = 243,
            label = "Real Madrid",
            imageUrl = "https://images.ctfassets.net/rs6bgs1g8dbr/" +
                    "3X9LsZRgy03pchwhQacpwY/f6a0a73d564f4e5653e88427c0b4a02c/Real_Madrid.png",
            isPopular = true
        ),
        position = Position(
            id = "18",
            shortLabel = "CAM",
            label = "Center Attacking Midfielder"
        ),
        playerAbilities = listOf(
            PlayerAbility(
                id = "trait1_4096",
                label = "Intercept",
                description = "Increased reach and possession retention in interceptions",
                imageUrl = "https://images.ctfassets.net/rs6bgs1g8dbr/" +
                        "5ysv7Iw8jDHl7sp1JXqIa7/d34004fed8c95bde083dcf8257391a70/Intercept.png",
                type = AbilityType(id = "playStyle", label = "Play Style")
            )
        ),
        teamMates = emptyList()
    )
}
