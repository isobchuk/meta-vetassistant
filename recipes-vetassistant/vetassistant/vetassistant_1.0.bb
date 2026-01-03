ESCRIPTION = "Vet Assistant Kiosk App"
LICENSE = "CLOSED"

SRC_URI = "file://vetassistant/ \
           file://vetassistant.service"

S = "${WORKDIR}/vetassistant"

DEPENDS += "qtbase-native qtbase qtdeclarative qtquickcontrols2 sqlite3"
inherit pkgconfig systemd

do_configure() {
    :
}

do_compile() {
    oe_runmake -C ${S} CONFIGURATION=release
}

do_install() {
    install -d ${D}${bindir}

    # Install the app binary
    install -m 0755 ${S}/output/release/bin/vetassistant ${D}${bindir}/vetassistant

    # Install QML files
    install -d ${D}${datadir}/vetassistant/gui/qt/qml
    cp -R ${S}/src/gui/qt/qml ${D}${datadir}/vetassistant/gui/qt
    install -d ${D}${datadir}/vetassistant/db/default
    cp -R ${S}/resources ${D}${datadir}/vetassistant/db/default
}

SYSTEMD_SERVICE_${PN} = "vetassistant.service"

FILES:${PN} =  "${bindir}/vetassistant ${datadir}/vetassistant"

