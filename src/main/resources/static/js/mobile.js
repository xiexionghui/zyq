function togglearticle(id) {
    if (id === "qyjj") {
        $("#qyjj").removeClass("hidden");
        $("#qywh").addClass("hidden");
    } else if (id === "qywh") {
        $("#qywh").removeClass("hidden");
        $("#qyjj").addClass("hidden");
    }
}

function toggleview(id) {
    if (id === "zlzs") {
        $("#zlzs").removeClass("hidden");
        $("#jcbg").addClass("hidden");
        $("#kjcg").addClass("hidden");
        $("#ryzz").addClass("hidden");
        $("#gltx").addClass("hidden");
    } else if (id === "jcbg") {
        $("#jcbg").removeClass("hidden");
        $("#zlzs").addClass("hidden");
        $("#kjcg").addClass("hidden");
        $("#ryzz").addClass("hidden");
        $("#gltx").addClass("hidden");
    } else if (id === "kjcg") {
        $("#kjcg").removeClass("hidden");
        $("#jcbg").addClass("hidden");
        $("#zlzs").addClass("hidden");
        $("#ryzz").addClass("hidden");
        $("#gltx").addClass("hidden");
    } else if (id === "ryzz") {
        $("#ryzz").removeClass("hidden");
        $("#jcbg").addClass("hidden");
        $("#zlzs").addClass("hidden");
        $("#gltx").addClass("hidden");
        $("#kjcg").addClass("hidden");
    } else if (id === "gltx") {
        $("#gltx").removeClass("hidden");
        $("#jcbg").addClass("hidden");
        $("#zlzs").addClass("hidden");
        $("#kjcg").addClass("hidden");
        $("#ryzz").addClass("hidden");
    }
}

