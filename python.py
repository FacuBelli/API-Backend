import requests
import json

categs = [
    {
      'title': 'Sunset Reflections',
      'description':
        'A realistic painting capturing the beauty of a sunset reflecting on a tranquil lake.',
      'categories': [
         'Landscape',
         'Nature'
      ],
      'styles': [ 'Realism' ],
      'themes': [ 'Sunsets' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 250,
      'stock': 1,
      'artistId': 3,
      'isHidden': False
    },
    {
      'title': 'Abstract Harmony',
      'description':
        'An abstract expressionist artwork that explores the concept of harmony through vibrant colors and dynamic shapes.',
      'categories': [ 'Abstract' ],
      'styles': [ 'Abstract Expressionism' ],
      'themes': [ 'Harmony' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 180,
      'stock': 1,
      'artistId': 3,
      'isHidden': False
    },
    {
      'title': 'Cityscape Rhythms',
      'description':
        'An impressionist cityscape painting that captures the dynamic rhythms of urban life.',
      'categories': [ 'Cityscape' ],
      'styles': [ 'Impressionism' ],
      'themes': [ 'Urban Life' ],
      'orientation':  'Vertical',
      'image': '',
      'price': 300,
      'stock': 0,
      'artistId': 4,
      'isHidden': False
    },
    {
      'title': 'Floral Serenity',
      'description':
        'A realistic depiction of a serene floral arrangement, showcasing the beauty of nature.',
      'categories': [ 'Floral' ],
      'styles': [ 'Realism' ],
      'themes': [ 'Nature' ],
      'orientation':  'Square',
      'image': '',
      'price': 150,
      'stock': 1,
      'artistId': 4,
      'isHidden': False
    },
    {
      'title': 'The Power Within',
      'description':
        'A surrealistic artwork that symbolizes the inner power and strength within every individual.',
      'categories': [ 'Abstract' ],
      'styles': [ 'Surrealism' ],
      'themes': [ 'Inner Power' ],
      'orientation':  'Vertical',
      'image': '',
      'price': 200,
      'stock': 1,
      'artistId': 5,
      'isHidden': False
    },
    {
      'title': 'Wilderness Escape',
      'description':
        'A realistic landscape painting depicting a peaceful wilderness scene, perfect for nature lovers.',
      'categories': [ 'Landscape' ],
      'styles': [ 'Realism' ],
      'themes': [ 'Wilderness' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 280,
      'stock':0,
      'artistId': 5,
      'isHidden': False
    },
    {
      'title': 'Industrial Elegance',
      'description':
        'A modernist painting showcasing the elegance of urban architecture in an industrial setting.',
      'categories': [ 'Cityscape' ],
      'styles': [ 'Modernism' ],
      'themes': [ 'Urban Architecture' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 320,
      'stock': 1,
      'artistId': 5,
      'isHidden': False
    },
    {
      'title': 'Dreamy Waters',
      'description':
        'An impressionist artwork capturing the dreamy and harmonious qualities of water in nature.',
      'categories': [ 'Landscape' ],
      'styles': [ 'Impressionism' ],
      'themes': [ 'Harmony' ],
      'orientation':  'Vertical',
      'image': '',
      'price': 180,
      'stock': 0,
      'artistId': 7,
      'isHidden': False
    },
    {
      'title': 'Ethereal Beauty',
      'description':
        'A symbolic floral painting that explores the ethereal beauty and ephemeral nature of flowers.',
      'categories': [ 'Floral' ],
      'styles': [ 'Symbolism' ],
      'themes': [ 'Nature' ],
      'orientation':  'Square',
      'image': '',
      'price': 200,
      'stock': 1,
      'artistId': 7,
      'isHidden': False
    },
    {
      'title': 'Urban Reflections',
      'description':
        'An impressionist cityscape painting reflecting the vibrancy and reflections of urban life.',
      'categories': [ 'Cityscape' ],
      'styles': [ 'Impressionism' ],
      'themes': [ 'Urban Life' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 300,
      'stock': 1,
      'artistId': 7,
      'isHidden': False
    },
    {
      'title': 'Celestial Symphony',
      'description':
        'A cubist abstract artwork inspired by the symphony of celestial bodies and cosmic harmony.',
      'categories': [ 'Abstract' ],
      'styles': [ 'Cubism' ],
      'themes': [ 'Celestial' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 350,
      'stock': 0,
      'artistId': 8,
      'isHidden': False
    },
    {
      'title': 'Tranquil Gardens',
      'description':
        'A realistic painting capturing the tranquility and beauty of a peaceful garden setting.',
      'categories': [ 'Gardens' ],
      'styles': [ 'Realism' ],
      'themes': [ 'Tranquility' ],
      'orientation':  'Vertical',
      'image': '',
      'price': 180,
      'stock': 1,
      'artistId': 8,
      'isHidden': False
    },
    {
      'title': 'Mindscapes',
      'description':
        'A surrealistic abstract artwork that explores the landscapes of the mind and dreams.',
      'categories': [ 'Abstract' ],
      'styles': [ 'Surrealism' ],
      'themes': [ 'Dreams' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 220,
      'stock': 1,
      'artistId': 11,
      'isHidden': False
    },
    {
      'title': 'Urban Jungle',
      'description':
        'A futuristic painting depicting an urban landscape transformed into a vibrant jungle-like environment.',
      'categories': [ 'Cityscape' ],
      'styles': [ 'Futurism' ],
      'themes': [ 'Urban Jungle' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 400,
      'stock': 1,
      'artistId': 11,
      'isHidden': False
    },
    {
      'title': 'Ocean Serenity',
      'description':
        'A realistic seascape painting capturing the serene beauty and vastness of the ocean.',
      'categories': [ 'Seascape' ],
      'styles': [ 'Realism' ],
      'themes': [ 'Ocean' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 280,
      'stock': 1,
      'artistId': 12,
      'isHidden': False
    },
    {
      'title': 'Abstract Cityscape - Urban Dreams',
      'description':
        'Urban dreams come alive in this abstract cityscape, where the bustling city takes on a vibrant, energetic form.',
      'categories': [ 'Cityscape' ],
      'styles': [ 'Abstract' ],
      'themes': [ 'Urban' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 250,
      'stock': 1,
      'artistId': 3,
      'isHidden': False
    },
    {
      'title': 'Impressionistic Seascape - Waves of Serenity',
      'description':
        'Waves of serenity wash over in this impressionistic seascape, capturing the tranquil beauty of the sea at sunset.',
      'categories': [
         'Landscape',
        'Seascape'
      ],
      'styles': [ 'Impressionism' ],
      'themes': [ 'Nature' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 350,
      'stock': 1,
      'artistId': 5,
      'isHidden': False
    },
    {
      'title': 'Abstract Floral - Blooming Reverie',
      'description':
        'Blooming reverie unfolds in this abstract floral, where colors and shapes dance in harmony like a floral symphony.',
      'categories': [ 'Floral' ],
      'styles': [ 'Abstract' ],
      'themes': [ 'Whimsical' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 200,
      'stock': 1,
      'artistId': 4,
      'isHidden': False
    },
    {
      'title': 'Realistic Still Life - Timeless Elegance',
      'description':
        'Timeless elegance is captured in this realistic still life, where everyday objects become symbols of beauty and grace.',
      'categories': [ 'Still Life' ],
      'styles': [ 'Realism' ],
      'themes': [ 'Traditional' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 300,
      'stock': 1,
      'artistId': 2,
      'isHidden': False
    },
    {
      'title': 'Abstract Wildlife - Jungle Symphony',
      'description':
        'Jungle symphony comes alive in this abstract wildlife painting, where the vibrant colors and dynamic shapes mimic the energy of the jungle.',
      'categories': [ 'Wildlife' ],
      'styles': [ 'Abstract' ],
      'themes': [ 'Urban' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 400,
      'stock': 1,
      'artistId': 1,
      'isHidden': False
    },
    {
      'title': 'Expressionistic Street Art - Urban Poetry',
      'description':
        'Urban poetry unfolds in this expressionistic street art, where the city streets become a canvas for raw emotion and bold expression.',
      'categories': [ 'Cityscape' ],
      'styles': [ 'Expressionism' ],
      'themes': [
        'Urban',
        'Street Art'
      ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 280,
      'stock': 1,
      'artistId': 3,
      'isHidden': False
    },
    {
      'title': 'Surrealistic Landscape - Dreamy Meadows',
      'description':
        'Dreamy meadows stretch into the horizon in this surrealistic landscape, where reality and fantasy blend into a harmonious scene.',
      'categories': [ 'Landscape' ],
      'styles': [ 'Surrealism' ],
      'themes': [ 'Dreamlike' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 320,
      'stock': 1,
      'artistId': 5,
      'isHidden': False
    },
    {
      'title': 'Minimalistic Abstract - Serene Simplicity',
      'description':
        'Serene simplicity is captured in this minimalistic abstract, where a few bold strokes create a powerful visual statement.',
      'categories': [ 'Abstract' ],
      'styles': [ 'Minimalism' ],
      'themes': [ 'Whimsical' ],
      'orientation':  'Square',
      'image': '',
      'price': 180,
      'stock': 1,
      'artistId': 4,
      'isHidden': False
    },
    {
      'title': 'Pop Art Portrait - Iconic Expression',
      'description':
        'Iconic expression shines through in this pop art portrait, where bold colors and graphic elements create a striking visual impact.',
      'categories': [ 'Portrait' ],
      'styles': [ 'Pop Art' ],
      'themes': [ 'Pop Art' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 260,
      'stock': 1,
      'artistId': 2,
      'isHidden': False
    },
    {
      'title': 'Cubist Still Life - Geometric Harmony',
      'description':
        'Geometric harmony unfolds in this cubist still life, where objects are deconstructed and reassembled in a dynamic composition.',
      'categories': [ 'Still Life' ],
      'styles': [ 'Cubism' ],
      'themes': [ 'Whimsical' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 290,
      'stock': 1,
      'artistId': 1,
      'isHidden': False
    },
    {
      'title': 'Fauvist Landscape - Vibrant Meadows',
      'description':
        "Vibrant meadows come alive in this fauvist landscape, where colors are bold and expressive, capturing the essence of nature's beauty.",
      'categories': [ 'Landscape' ],
      'styles': [ 'Fauvism' ],
      'themes': [ 'Nature' ],
      'orientation':  'Vertical',
      'image': '',
      'price': 270,
      'stock': 1,
      'artistId': 3,
      'isHidden': False
    },
    {
      'title': 'Abstract Architecture - Urban Illusions',
      'description':
        'Urban illusions unfold in this abstract architecture, where buildings and structures take on a surreal, dreamlike quality.',
      'categories': [ 'Cityscape' ],
      'styles': [ 'Abstract' ],
      'themes': [ 'Urban' ],
      'orientation':  'Square',
      'image': '',
      'price': 320,
      'stock': 1,
      'artistId': 5,
      'isHidden': False
    },
    {
      'title': 'Realistic Wildlife - Majestic Elephants',
      'description':
        'Majestic elephants are portrayed in this realistic wildlife painting, capturing the beauty and grace of these magnificent creatures.',
      'categories': [ 'Wildlife' ],
      'styles': [ 'Realism' ],
      'themes': [ 'Nature' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 380,
      'stock': 1,
      'artistId': 4,
      'isHidden': False
    },
    {
      'title': 'Abstract Seascape - Oceanic Rhythms',
      'description':
        'Oceanic rhythms come alive in this abstract seascape, where the ebb and flow of the sea are captured in a dynamic, fluid composition.',
      'categories': [ 'Seascape' ],
      'styles': [ 'Abstract' ],
      'themes': [ 'Ocean' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 300,
      'stock': 1,
      'artistId': 2,
      'isHidden': False
    },
    {
      'title': 'Surrealistic Wildlife - Enchanted Forest',
      'description':
        'Enchanted forest comes alive in this surrealistic wildlife painting, where animals and nature blend into a mystical, dreamlike scene.',
      'categories': [ 'Wildlife' ],
      'styles': [ 'Surrealism' ],
      'themes': [ 'Dreamlike' ],
      'orientation':  'Vertical',
      'image': '',
      'price': 350,
      'stock': 1,
      'artistId': 1,
      'isHidden': False
    },
    {
      'title': 'Pop Art Still Life - Modern Icons',
      'description':
        'Modern icons are portrayed in this pop art still life, where everyday objects become symbols of contemporary culture.',
      'categories': [ 'Still Life' ],
      'styles': [ 'Pop Art' ],
      'themes': [ 'Modern' ],
      'orientation':  'Vertical',
      'image': '',
      'price': 270,
      'stock': 1,
      'artistId': 3,
      'isHidden': False
    },
    {
      'title': 'Abstract Portrait - Inner Reflections',
      'description':
        'Inner reflections are captured in this abstract portrait, where emotions and thoughts are expressed through color and form.',
      'categories': [ 'Portrait' ],
      'styles': [ 'Abstract' ],
      'themes': [ 'Emotions' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 230,
      'stock': 1,
      'artistId': 5,
      'isHidden': False
    },
    {
      'title': 'Cubist Landscape - Geometric Nature',
      'description':
        'Geometric nature is portrayed in this cubist landscape, where the natural world is reimagined through a prism of shapes and angles.',
      'categories': [ 'Landscape' ],
      'styles': [ 'Cubism' ],
      'themes': [ 'Nature' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 290,
      'stock': 1,
      'artistId': 2,
      'isHidden': False
    },
    {
      'title': 'Abstract Wildlife - Jungle Rhythms',
      'description':
        'Jungle rhythms come alive in this abstract wildlife painting, where the energy and movement of the jungle are captured in a dynamic composition.',
      'categories': [ 'Wildlife' ],
      'styles': [ 'Abstract' ],
      'themes': [ 'Urban' ],
      'orientation':  'Horizontal',
      'image': '',
      'price': 380,
      'stock': 1,
      'artistId': 4,
      'isHidden': False
    },
    {
      'title': 'Realistic Portrait - Timeless Beauty',
      'description':
        "Timeless beauty is captured in this realistic portrait, where the subject's features are portrayed with remarkable detail and precision.",
      'categories': [ 'Portrait' ],
      'styles': [ 'Realism' ],
      'themes': [ 'Emotions' ],
      'orientation':  'Square',
      'image': '',
      'price': 320,
      'stock': 1,
      'artistId': 1,
      'isHidden': False
    }
]

for user in categs:
  r = requests.post('http://localhost:8080/artwork', headers={'Content-Type': 'application/json'}, data=json.dumps(user))
  print(r.json())
  print()