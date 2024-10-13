// main project
#let template(
  title: "",
  subtitle: "",
  author: "",
  affiliation: "",
  year: none,
  class: none,
  date: datetime.today().display(),
  logo: image("Ensimag.png"),
  main-color: rgb("E94845"),
  alpha: 60%,
  color-words: (),
  body,
  logo2: false,
) = {
  set document(author: author, title: title)

  // Save heading and body font families in variables.
  let body-font = "Source Sans Pro"
  let title-font = "Barlow"

  // Set colors
  let primary-color = main-color // alpha = 100%
  // change alpha of primary color
  let secondary-color = color.mix(color.rgb(100%, 100%, 100%, alpha), primary-color, space:rgb)

  // highlight important words
  show regex(if color-words.len() == 0 { "$ " } else { color-words.join("|") }): text.with(weight: "bold", fill: primary-color)

  // //customize look of figure
  // set figure.caption(separator: [ --- ], position: top)

  //customize inline raw code
  show raw.where(block: false) : it => h(0.5em) + box(fill: primary-color.lighten(90%), outset: 0.2em, it) + h(0.5em)

  // Set body font family.
  set text(font: body-font, lang: "fr", 12pt)
  show heading: set text(font: title-font, fill: primary-color)

  //heading numbering
  set heading(numbering: (..nums) => {
    let level = nums.pos().len()
    // only level 1 and 2 are numbered
    let pattern = if level == 1 {
      "I."
    } else if level == 2 {
      "I.1."
    }
    if pattern != none {
      numbering(pattern, ..nums)
    }
  })

  // add space for heading
  show heading.where(level:1): it => it + v(0.5em)
 
  // Set link style
  show link: it => underline(text(fill: primary-color, it))

  //numbered list colored
  set enum(indent: 1em, numbering: n => [#text(fill: primary-color, numbering("1.", n))])

  //unordered list colored
  set list(indent: 1em, marker: n => [#text(fill: primary-color, "•")])


  // display of outline entries
  show outline.entry: it => text(size: 12pt, weight: "regular",it)

  // Title page.
  // Logo at top right if given
  if logo != none {
    set image(width: 6cm)
    place(top + right, logo)
    if logo2 == true{
      place(center, dy : 85%, image("Ubiqube.png"))
    }
  }
  // decorations at top left
  place(top + left, dx: -35%, dy: -28%, circle(radius: 150pt, fill: primary-color))
  place(top + left, dx: -10%, circle(radius: 75pt, fill: secondary-color))
  
  // decorations at bottom right
  place(bottom +right, dx: 40%, dy: 30%, circle(radius: 150pt, fill: secondary-color))

  
  v(2fr)

  align(center, text(font: title-font, 3em, weight: 700, title))
  v(2em, weak: true)
  align(center, text(font: title-font, 2em, weight: 700, subtitle))
  v(2em, weak: true)
  align(center, text(1.1em, date.display()))

  v(2fr)

  // Author information.
  align(center)[      #text(author, 14pt, weight: "bold") \      #affiliation \ #year\ #emph[#class] 
    ]

  pagebreak()

 
  // Main body.
  set page(
    numbering: "1 / 1", 
    number-align: center, 
    )

  set page(
    header: [#emph()[#title]]
  )
  

  set par(justify: true)

  
   // Table of contents.
  set outline(fill: repeat[~.], indent: 1em) 
  show outline.entry.where(level: 1): it => text(font: title-font, size: 15pt, weight: "bold", strong(it))
  show outline: it => text(font: title-font, size: 15pt, weight: "regular", it)

  linebreak()
  outline()

  pagebreak()
  body
 
}


//useful functions
//set block-quote
#let blockquote = rect.with(stroke: (left: 2.5pt + luma(170)), inset: (left: 1em))

// use primary-color and secondary-color in main
#let primary-color = rgb("E94845")
#let secondary-color = rgb(255, 80, 69, 60%)
