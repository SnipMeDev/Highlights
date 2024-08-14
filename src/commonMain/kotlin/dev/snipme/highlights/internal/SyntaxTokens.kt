package dev.snipme.highlights.internal

internal object SyntaxTokens {

    val C_KEYWORDS_OLD = """
        auto,break,case,char,const,continue,default,do,double,else,enum,extern,float,for,goto,if,
        int,long,register,return,short,signed,sizeof,static,struct,switch,typedef,union,unsigned,
        void,volatile,while
    """.toTokenList()

    val C_KEYWORDS = setOf(
        "auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else",
        "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return",
        "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned",
        "void", "volatile", "while"
    )

    val CPP_KEYWORDS_OLD = """
        asm,auto,bool,break,case,catch,char,class,const,const_cast,continue,default,delete,do,
        double,dynamic_cast,else,enum,explicit,export,extern,false,float,for,friend,goto,if,inline,
        int,long,mutable,namespace,new,operator,private,protected,public,register,reinterpret_cast,
        return,short,signed,sizeof,static,static_cast,struct,switch,template,this,throw,true,try,
        typedef,typeid,typename,union,unsigned,using,virtual,void,volatile,wchar_t,while
    """.toTokenList()

    val CPP_KEYWORDS = setOf(
        "asm", "auto", "bool", "break", "case", "catch", "char", "class", "const", "const_cast",
        "continue", "default", "delete", "do", "double", "dynamic_cast", "else", "enum",
        "explicit", "export", "extern", "false", "float", "for", "friend", "goto", "if", "inline",
        "int", "long", "mutable", "namespace", "new", "operator", "private", "protected", "public",
        "register", "reinterpret_cast", "return", "short", "signed", "sizeof", "static",
        "static_cast", "struct", "switch", "template", "this", "throw", "true", "try", "typedef",
        "typeid", "typename", "union", "unsigned", "using", "virtual", "void", "volatile",
        "wchar_t", "while"
    )

    val JAVA_KEYWORDS_OLD = """
        abstract,assert,boolean,break,byte,case,catch,char,class,const,continue,default,do,double,
        else,enum,extends,final,finally,float,for,goto,if,implements,import,instanceof,int,
        interface,long,native,new,null,package,private,protected,public,return,short,static,
        strictfp,super,switch,synchronized,this,throw,throws,transient,try,void,volatile,while
    """.toTokenList()

    val JAVA_KEYWORDS = setOf(
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
        "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
        "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
        "interface", "long", "native", "new", "null", "package", "private", "protected", "public",
        "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw",
        "throws", "transient", "try", "void", "volatile", "while"
    )


    val KOTLIN_KEYWORDS_OLD = """
        actual,abstract,annotation,as,as?,break,by,catch,class,companion,const,constructor,continue,
        coroutine,crossinline,data,delegate,dynamic,do,else,enum,expect,external,false,final,
        finally,for,fun,get,if,import,in,!in,infix,inline,interface,internal,is,!is,lazy,lateinit,
        native,null,object,open,operator,out,override,package,private,protected,public,reified,
        return,sealed,set,super,suspend,tailrec,this,throw,true,try,typealias,typeof,val,var,vararg,
        when,while,yield
    """.toTokenList()

    val KOTLIN_KEYWORDS = setOf(
        "actual", "abstract", "annotation", "as", "as?", "break", "by", "catch", "class",
        "companion", "const", "constructor", "continue", "coroutine", "crossinline", "data",
        "delegate", "dynamic", "do", "else", "enum", "expect", "external", "false", "final",
        "finally", "for", "fun", "get", "if", "import", "in", "!in", "infix", "inline", "interface",
        "internal", "is", "!is", "lazy", "lateinit", "native", "null", "object", "open", "operator",
        "out", "override", "package", "private", "protected", "public", "reified", "return", "sealed",
        "set", "super", "suspend", "tailrec", "this", "throw", "true", "try", "typealias", "typeof",
        "val", "var", "vararg", "when", "while", "yield"
    )

    val RUST_KEYWORDS_OLD = """
        as,async,await,break,const,continue,crate,dyn,else,enum,extern,false,fn,for,if,impl,in,let,
        loop,match,mod,move,mut,pub,ref,return,Self,self,static,struct,super,trait,true,type,union,
        unsafe,use,where,while,abstract,become,box,do,final,macro,override,priv,try,typeof,
        unsized,virtual,yield
    """.toTokenList()

    val RUST_KEYWORDS = setOf(
        "as", "async", "await", "break", "const", "continue", "crate", "dyn", "else", "enum",
        "extern", "false", "fn", "for", "if", "impl", "in", "let", "loop", "match", "mod", "move",
        "mut", "pub", "ref", "return", "Self", "self", "static", "struct", "super", "trait", "true",
        "type", "union", "unsafe", "use", "where", "while", "abstract", "become", "box", "do",
        "final", "macro", "override", "priv", "try", "typeof", "unsized", "virtual", "yield"
    )

    val CSHARP_KEYWORDS_OLD = """
        abstract,as,base,bool,break,byte,case,catch,char,checked,class,const,continue,decimal,
        default,delegate,do,double,else,enum,event,explicit,extern,false,finally,fixed,float,for,
        foreach,goto,if,implicit,in,int,interface,internal,is,lock,long,namespace,new,null,object,
        operator,out,override,params,private,protected,public,readonly,ref,return,sbyte,sealed,
        short,sizeof,stackalloc,static,string,struct,switch,this,throw,true,try,typeof,uint,ulong,
        unchecked,unsafe,ushort,using,virtual,void,volatile,while
    """.toTokenList()

    val CSHARP_KEYWORDS = setOf(
        "abstract", "as", "base", "bool", "break", "byte", "case", "catch", "char", "checked",
        "class", "const", "continue", "decimal", "default", "delegate", "do", "double", "else",
        "enum", "event", "explicit", "extern", "false", "finally", "fixed", "float", "for",
        "foreach", "goto", "if", "implicit", "in", "int", "interface", "internal", "is", "lock",
        "long", "namespace", "new", "null", "object", "operator", "out", "override", "params",
        "private", "protected", "public", "readonly", "ref", "return", "sbyte", "sealed", "short",
        "sizeof", "stackalloc", "static", "string", "struct", "switch", "this", "throw", "true",
        "try", "typeof", "uint", "ulong", "unchecked", "unsafe", "ushort", "using", "virtual",
        "void", "volatile", "while"
    )

    val COFFEE_SCRIPT_KEYWORDS_OLD = """
        =,->,Infinity,NaN,and,arguments,await,break,by,case,catch,class,continue,debugger,delete,
        defer,default,do,else,export,extends,false,finally,for,function,if,import,in,instanceof,is,
        isnt,let,loop,new,no,not,null,of,on,or,package,return,super,switch,this,throw,true,try,
        typeof,unless,undefined,var,wait,when,with,yield
    """.toTokenList()

    val COFFEE_SCRIPT_KEYWORDS = setOf(
        "=", "->", "Infinity", "NaN", "and", "arguments", "await", "break", "by", "case", "catch",
        "class", "continue", "debugger", "delete", "defer", "default", "do", "else", "export",
        "extends", "false", "finally", "for", "function", "if", "import", "in", "instanceof", "is",
        "isnt", "let", "loop", "new", "no", "not", "null", "of", "on", "or", "package", "return",
        "super", "switch", "this", "throw", "true", "try", "typeof", "unless", "undefined", "var",
        "wait", "when", "with", "yield"
    )

    val JAVASCRIPT_KEYWORDS_OLD = """
        async,await,boolean,break,case,catch,class,const,continue,debugger,default,delete,do,else,
        enum,export,extends,false,finally,for,function,if,implements,import,in,instanceof,interface,
        let,new,null,package,private,protected,public,return,super,switch,this,throw,true,try,typeof,
        var,void,while,with,yield
    """.toTokenList()

    val JAVASCRIPT_KEYWORDS = setOf(
        "async", "await", "boolean", "break", "case", "catch", "class", "const", "continue",
        "debugger", "default", "delete", "do", "else", "enum", "export", "extends", "false",
        "finally", "for", "function", "if", "implements", "import", "in", "instanceof", "interface",
        "let", "new", "null", "package", "private", "protected", "public", "return", "super",
        "switch", "this", "throw", "true", "try", "typeof", "var", "void", "while", "with", "yield"
    )

    val PERL_KEYWORDS_OLD = """
        __DATA__,__END__,__FILE__,__LINE__,__PACKAGE__,and,cmp,continue,do,else,elsif,eq,eval,for,
        foreach,goto,gt,if,last,last,le,lt,my,ne,next,no,not,or,package,redo,ref,return,sub,unless,
        until,use,while,xor
    """.toTokenList()

    val PERL_KEYWORDS = setOf(
        "__DATA__", "__END__", "__FILE__", "__LINE__", "__PACKAGE__", "and", "cmp", "continue",
        "do", "else", "elsif", "eq", "eval", "for", "foreach", "goto", "gt", "if", "last", "last",
        "le", "lt", "my", "ne", "next", "no", "not", "or", "package", "redo", "ref", "return", "sub",
        "unless", "until", "use", "while", "xor"
    )

    val PYTHON_KEYWORDS_OLD = """
        False,True,and,as,assert,async,await,break,class,continue,def,del,elif,else,except,finally,
        for,from,global,if,import,in,is,lambda,nonlocal,not,or,pass,raise,return,try,while,with,
        yield
    """.toTokenList()

    val PYTHON_KEYWORDS = setOf(
        "False", "True", "and", "as", "assert", "async", "await", "break", "class", "continue",
        "def", "del", "elif", "else", "except", "finally", "for", "from", "global", "if", "import",
        "in", "is", "lambda", "nonlocal", "not", "or", "pass", "raise", "return", "try", "while",
        "with", "yield"
    )

    val RUBY_KEYWORDS_OLD = """
        __ENCODING__,__END__,__FILE__,__LINE__,BEGIN,END,alias,and,begin,break,case,class,def,
        defined?,do,else,elsif,end,ensure,false,for,if,in,module,next,nil,not,or,redo,rescue,retry,
        return,self,super,then,true,undef,unless,until,when,while,yield
    """.toTokenList()

    val RUBY_KEYWORDS = setOf(
        "__ENCODING__", "__END__", "__FILE__", "__LINE__", "BEGIN", "END", "alias", "and", "begin",
        "break", "case", "class", "def", "defined?", "do", "else", "elsif", "end", "ensure", "false",
        "for", "if", "in", "module", "next", "nil", "not", "or", "redo", "rescue", "retry", "return",
        "self", "super", "then", "true", "undef", "unless", "until", "when", "while", "yield"
    )

    val SH_KEYWORDS_OLD = """
        alias,bg,bind,break,builtin,caller,cd,command,compgen,complete,compopt,continue,declare,
        dirs,disown,echo,enable,eval,exec,exit,export,fc,fg,getopts,hash,help,history,jobs,kill,let,
        local,logout,popd,printf,pushd,pwd,read,readonly,return,set,shift,shopt,source,suspend,test
    """.toTokenList()

    val SH_KEYWORDS = setOf(
        "alias", "bg", "bind", "break", "builtin", "caller", "cd", "command", "compgen", "complete",
        "compopt", "continue", "declare", "dirs", "disown", "echo", "enable", "eval", "exec", "exit",
        "export", "fc", "fg", "getopts", "hash", "help", "history", "jobs", "kill", "let", "local",
        "logout", "popd", "printf", "pushd", "pwd", "read", "readonly", "return", "set", "shift",
        "shopt", "source", "suspend", "test"
    )

    val SWIFT_KEYWORDS_OLD = """
        _,associatedtype,class,deinit,enum,extension,fileprivate,func,import,init,inout,internal,
        let,open,operator,private,precedencegroup,protocol,public,rethrows,static,struct,subscript,
        typealias,var,break,case,catch,continue,default,defer,do,else,fallthrough,for,guard,if,in,
        repeat,return,throw,switch,where,while,Any,as,await,catch,false,is,nil,rethrows,self,Self,
        super,throw,throws,true,try,#available,#colorLiteral,#else,#elseif,#endif,#fileLiteral,#if,
        #imageLiteral,#keyPath,#selector,#sourceLocation,#unavailable,associativity,convenience,
        didSet,dynamic,final,get,indirect,infix,lazy,left,mutating,none,nonmutating,optional,
        override,postfix,precedence,prefix,Protocol,required,right,set,some,Type,unowned,weak,
        willSet
    """.toTokenList()

    val SWIFT_KEYWORDS = setOf(
        "_", "associatedtype", "class", "deinit", "enum", "extension", "fileprivate", "func",
        "import", "init", "inout", "internal", "let", "open", "operator", "private",
        "precedencegroup", "protocol", "public", "rethrows", "static", "struct", "subscript",
        "typealias", "var", "break", "case", "catch", "continue", "default", "defer", "do", "else",
        "fallthrough", "for", "guard", "if", "in", "repeat", "return", "throw", "switch", "where",
        "while", "Any", "as", "await", "catch", "false", "is", "nil", "rethrows", "self", "Self",
        "super", "throw", "throws", "true", "try", "#available", "#colorLiteral",
        "#else", "#elseif", "#endif", "#fileLiteral", "#if", "#imageLiteral", "#keyPath",
        "#selector", "#sourceLocation", "#unavailable", "associativity", "convenience", "didSet",
        "dynamic", "final", "get", "indirect", "infix", "lazy", "left", "mutating", "none",
        "nonmutating", "optional", "override", "postfix", "precedence", "prefix", "Protocol",
        "required", "right", "set", "some", "Type", "unowned", "weak", "willSet"
    )

    val DART_KEYWORDS_OLD = """
        abstract,as,assert,async,await,base,break,case,catch,class,const,continue,covariant,default,
        deferred,do,dynamic,else,enum,export,extends,external,factory,false,final,finally,for,get,
        if,implements,import,in,interface,is,late,library,mixin,new,null,on,operator,part,required,
        rethrow,return,sealed,set,show,static,super,switch,this,throw,true,try,var,void,when,with,
        while,yield
    """.toTokenList()

    val DART_KEYWORDS = setOf(
        "abstract", "as", "assert", "async", "await", "base", "break", "case", "catch", "class",
        "const", "continue", "covariant", "default", "deferred", "do", "dynamic", "else", "enum",
        "export", "extends", "external", "factory", "false", "final", "finally", "for", "get", "if",
        "implements", "import", "in", "interface", "is", "late", "library", "mixin", "new", "null",
        "on", "operator", "part", "required", "rethrow", "return", "sealed", "set", "show", "static",
        "super", "switch", "this", "throw", "true", "try", "var", "void", "when", "with", "while",
        "yield"
    )

    val GO_KEYWORDS_OLD = """
        break,case,chan,const,continue,default,defer,else,fallthrough,false,for,func,go,goto,if,
        import,interface,map,package,range,return,select,struct,switch,type,var,true
    """.toTokenList()

    val GO_KEYWORDS = setOf(
        "break", "case", "chan", "const", "continue", "default", "defer", "else", "fallthrough",
        "false", "for", "func", "go", "goto", "if", "import", "interface", "map", "package", "range",
        "return", "select", "struct", "switch", "type", "var", "true"
    )

    val PHP_KEYWORDS_OLD = """
        __halt_compiler,abstract,and,array,as,break,callable,case,catch,class,clone,const,continue,
        declare,default,die,do,echo,else,elseif,empty,enddeclare,endfor,endforeach,endif,endswitch,
        endwhile,eval,exit,extends,final,finally,fn,for,foreach,function,global,goto,if,implements,
        include,include_once,instanceof,insteadof,interface,isset,list,match,new,or,print,private,
        protected,public,require,require_once,return,static,switch,throw,trait,try,unset,use,var,
        while,xor,yield
    """.toTokenList()

    val PHP_KEYWORDS = setOf(
        "__halt_compiler", "abstract", "and", "array", "as", "break", "callable", "case", "catch",
        "class", "clone", "const", "continue", "declare", "default", "die", "do", "echo", "else",
        "elseif", "empty", "enddeclare", "endfor", "endforeach", "endif", "endswitch", "endwhile",
        "eval", "exit", "extends", "final", "finally", "fn", "for", "foreach", "function", "global",
        "goto", "if", "implements", "include", "include_once", "instanceof", "insteadof", "interface",
        "isset", "list", "match", "new", "or", "print", "private", "protected", "public", "require",
        "require_once", "return", "static", "switch", "throw", "trait", "try", "unset", "use", "var",
        "while", "xor", "yield"
    )

    val TYPESCRIPT_KEYWORDS_OLD = """
        abstract,as,asserts,await,break,case,catch,class,const,constructor,continue,debugger,
        default, delete,do,else,enum,export,extends,false,finally,for,from,function,get,if,
        implements,import,in,infer,instanceof,interface,is,keyof,let,module,namespace,new,null,
        number,object,package,private,protected,public,readonly,require,global,return,set,static,
        string,super,switch,this,throw,true,try,type,typeof,undefined,unique,unknown,var,void,while,
        with,yield
    """.toTokenList()

    val TYPESCRIPT_KEYWORDS = setOf(
        "abstract", "as", "asserts", "await", "break", "case", "catch", "class", "const",
        "constructor", "continue", "debugger", "default", "delete", "do", "else", "enum", "export",
        "extends", "false", "finally", "for", "from", "function", "get", "if", "implements", "import",
        "in", "infer", "instanceof", "interface", "is", "keyof", "let", "module", "namespace", "new",
        "null", "number", "object", "package", "private", "protected", "public", "readonly", "require",
        "global", "return", "set", "static", "string", "super", "switch", "this", "throw", "true",
        "try", "type", "typeof", "undefined", "unique", "unknown", "var", "void", "while", "with",
        "yield"
    )

    val ALL_KEYWORDS_OLD = (C_KEYWORDS_OLD + CPP_KEYWORDS_OLD + JAVA_KEYWORDS_OLD + KOTLIN_KEYWORDS_OLD +
            RUST_KEYWORDS_OLD + CSHARP_KEYWORDS_OLD + COFFEE_SCRIPT_KEYWORDS_OLD + JAVASCRIPT_KEYWORDS_OLD +
            PERL_KEYWORDS_OLD + PYTHON_KEYWORDS_OLD + RUBY_KEYWORDS_OLD + SH_KEYWORDS_OLD +
            SWIFT_KEYWORDS_OLD + DART_KEYWORDS_OLD + GO_KEYWORDS_OLD + PHP_KEYWORDS_OLD +
            TYPESCRIPT_KEYWORDS_OLD).toSet()

    val ALL_KEYWORDS = (C_KEYWORDS + CPP_KEYWORDS + JAVA_KEYWORDS + KOTLIN_KEYWORDS +
            RUST_KEYWORDS + CSHARP_KEYWORDS + COFFEE_SCRIPT_KEYWORDS + JAVASCRIPT_KEYWORDS +
            PERL_KEYWORDS + PYTHON_KEYWORDS + RUBY_KEYWORDS + SH_KEYWORDS +
            SWIFT_KEYWORDS + DART_KEYWORDS + GO_KEYWORDS + PHP_KEYWORDS +
            TYPESCRIPT_KEYWORDS).toSet()

    // TODO Migrate to list of chars
    val TOKEN_DELIMITERS =
        listOf(" ", ",", ".", ":", ";", "(", ")", "=", "{", "}", "<", ">", "\r", "\n")
    val STRING_DELIMITERS = listOf("\'", "\"", "\"\"\"")
    val COMMENT_DELIMITERS = listOf("//", "#")

    // TODO Add support for other other languages like Dart or Python
    val MULTILINE_COMMENT_DELIMITERS = listOf(Pair("/*", "*/"))
    val PUNCTUATION_CHARACTERS = listOf(",", ".", ":", ";")
    val MARK_CHARACTERS = listOf("(", ")", "=", "{", "}", "<", ">", "-", "+", "[", "]", "|", "&")

    private fun String.toTokenList() = trimIndent().trim().trim('\n').split(",").toSet()
}

